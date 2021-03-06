// Name: control_unit.v
// Module: CONTROL_UNIT
// Output: RF_DATA_W  : Data to be written at register file address RF_ADDR_W
//         RF_ADDR_W  : Register file address of the memory location to be written
//         RF_ADDR_R1 : Register file address of the memory location to be read for RF_DATA_R1
//         RF_ADDR_R2 : Register file address of the memory location to be read for RF_DATA_R2
//         RF_READ    : Register file Read signal
//         RF_WRITE   : Register file Write signal
//         ALU_OP1    : ALU operand 1
//         ALU_OP2    : ALU operand 2
//         ALU_OPRN   : ALU operation code

//         
// Input:  RF_DATA_R1 : Data at ADDR_R1 address
//         RF_DATA_R2 : Data at ADDR_R1 address
//         ALU_RESULT    : ALU output data
//         CLK        : Clock signal
//         RST        : Reset signal
//
//
// Notes: - Control unit synchronize operations of a processor
//
// Revision History:
//
// Version	Date		Who		email			note
//------------------------------------------------------------------------------------------
//  1.0     Sep 10, 2014	Kaushik Patra	kpatra@sjsu.edu		Initial creation
//  1.1     Oct 19, 2014    Kaushik Patra   	kpatra@sjsu.edu       Added ZERO status output
//  1.2	 Sep 28, 2015	Feiling Jia		feiling.jia@sjsu.edu	 Removed memory access and control
//------------------------------------------------------------------------------------------
`include "prj_definition.v"
module CONTROL_UNIT(RF_DATA_W, RF_ADDR_W, RF_ADDR_R1, RF_ADDR_R2, RF_READ, RF_WRITE,
                    ALU_OP1, ALU_OP2, ALU_OPRN,
                    RF_DATA_R1, RF_DATA_R2, ALU_RESULT, ZERO, CLK, RST, instruction); 

//Input signals
input [`DATA_INDEX_LIMIT:0] instruction;	
// Output signals
// Outputs for register file 
output reg [`DATA_INDEX_LIMIT:0] RF_DATA_W;
output reg [`ADDRESS_INDEX_LIMIT:0] RF_ADDR_W, RF_ADDR_R1, RF_ADDR_R2;
output reg RF_READ, RF_WRITE;
// Outputs for ALU
output reg [`DATA_INDEX_LIMIT:0]  ALU_OP1, ALU_OP2;
output reg [`ALU_OPRN_INDEX_LIMIT:0] ALU_OPRN;

// Input signals
input [`DATA_INDEX_LIMIT:0] RF_DATA_R1, RF_DATA_R2, ALU_RESULT;
input ZERO, CLK, RST;

//Registers
reg [`DATA_INDEX_LIMIT:0] PC_REG;
reg [`DATA_INDEX_LIMIT:0] INST_REG;
reg [`DATA_INDEX_LIMIT:0] data_to_mem;

reg [5:0] opcode;
reg [5:0] funct;
reg [4:0] rs;
reg [4:0] rt;



wire [2:0] proc_state;

PROC_SM state_machine(.STATE(proc_state),.CLK(CLK),.RST(RST));

	initial
        PC_REG = `INST_START_ADDR;
		
always @ (proc_state)
begin
	case(proc_state)
		`PROC_FETCH:
		begin
			
			PC_REG = PC_REG + 4;
			RF_WRITE = 0;
			RF_READ = 0;
		end
		`PROC_DECODE:
		begin
			INST_REG = instruction;
			RF_READ = 1;
			RF_WRITE = 0;
			opcode = instruction[0 +: 6];
			funct = instruction[26 +: 6];
			RF_ADDR_R1 = rs;
			RF_ADDR_R2 = rt;
			
		end
		`PROC_EXE:
		begin
			RF_READ = 0;
			RF_WRITE = 0;
			case(opcode)
				6'h00:
				begin
					ALU_OP1 = RF_DATA_R1;
					ALU_OP2 = RF_DATA_R2;
					case(funct)
						6'h20: 
							ALU_OPRN = 'h01;
						6'h22: 
							ALU_OPRN = 'h02; 
						6'h2c:
							ALU_OPRN = 'h03; 
						6'h24: 
							ALU_OPRN = 'h06; 
						6'h25: 
							ALU_OPRN = 'h07;
						6'h27: 
							ALU_OPRN = 'h08; 
						6'h2a:
							ALU_OPRN = 'h09; 
						6'h00: 
							ALU_OPRN = 'h04; 
						6'h02: 
							ALU_OPRN = 'h05; 
						default:/*Error*/$write("Error: Instruction does not exist\n");
					endcase
				end
			endcase
		end
	
	endcase
end
endmodule

//------------------------------------------------------------------------------------------
// Module: CONTROL_UNIT
// Output: STATE      : State of the processor
//         
// Input:  CLK        : Clock signal
//         RST        : Reset signal
//
//
// Notes: - Processor continuously cycle within fetch, decode, execute state. State values are in the prj_definition.v
//
// Revision History:
//
// Version	Date		Who		email			note
//------------------------------------------------------------------------------------------
//  1.0     Sep 10, 2014	Kaushik Patra	kpatra@sjsu.edu		Initial creation
//  1.2	 Sept 28, 2015 	Feiling Jia		feiling.jia@sjsu.edu	Limit the states to fetch, decode, and execute
//------------------------------------------------------------------------------------------
module PROC_SM(STATE,CLK,RST);
// list of inputs
input CLK, RST;
// list of outputs
output [2:0] STATE;

reg [2:0] NEXT_STATE;
reg [2:0] STATE;
// TBD - implement the state machine here

initial
begin
    STATE= 2'bxx;
    NEXT_STATE=`PROC_FETCH;
end

    always @ (negedge RST)
    begin
       STATE = 2'bxx;
       NEXT_STATE=`PROC_FETCH;
    end
	   
    always @ (posedge CLK)
    begin

        case (NEXT_STATE)
        `PROC_FETCH :
        begin               
            STATE = NEXT_STATE;
            NEXT_STATE = `PROC_DECODE;
        end
        `PROC_DECODE :
        begin   
            STATE = NEXT_STATE;
            NEXT_STATE = `PROC_EXE;
        end
        `PROC_EXE :
        begin   
            STATE = NEXT_STATE;
            NEXT_STATE = `PROC_FETCH;
        end
		
		default:
        begin
            STATE = 2'bxx;
            NEXT_STATE =`PROC_FETCH;
        end
        endcase
	end

endmodule