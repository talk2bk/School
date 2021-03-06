// Name: alu.v
// Module: ALU
// Input: OP1[32] - operand 1
//        OP2[32] - operand 2
//        OPRN[6] - instruction function code
// Output: OUT[32] - output result for the operation
//
// Notes: 32 bit combinatorial ALU
// 
//
// Revision History:
//
// Version	Date		Who		email			note
//------------------------------------------------------------------------------------------
//  1.0     Sep 10, 2014	Kaushik Patra	kpatra@sjsu.edu		Initial creation
//  1.1     Oct 19, 2014        Kaushik Patra   kpatra@sjsu.edu         Added ZERO status output
//  1.2	    Sep 28, 2015	Feiling Jia	feiling.jia@sjsu.edu	Modified the instruction set
//------------------------------------------------------------------------------------------
//
`include "prj_definition.v"
module ALU(OUT, ZERO, OP1, OP2, OPRN);
// input list
input [`DATA_INDEX_LIMIT:0] OP1; // operand 1
input [`DATA_INDEX_LIMIT:0] OP2; // operand 2
input [`ALU_OPRN_INDEX_LIMIT:0] OPRN; // operation code

// output list
output OUT; // result of the operation.
output ZERO;
//assign ZERO = (OUT === 0) ? 1 : 0; //if out = 0 0 else 1

output reg [`DATA_INDEX_LIMIT:0]OUT;
output reg [`DATA_INDEX_LIMIT:0]ZERO;

always @(OP1 or OP2 or OPRN)
begin	

case(OPRN)	
	`ALU_OPRN_WIDTH'h01 : OUT = OP1 + OP2; // addition	
	`ALU_OPRN_WIDTH'h02 : OUT = OP1 - OP2; // subtraction
	`ALU_OPRN_WIDTH'h03 : OUT = OP1 * OP2; // multiply
	`ALU_OPRN_WIDTH'h04 : OUT = OP1 << OP2; // shift left logical
	`ALU_OPRN_WIDTH'h05 : OUT = OP1 >> OP2; // shift right logical (NEED TO FILL WITH ZEROS)
	`ALU_OPRN_WIDTH'h06 : OUT = OP1 & OP2; // and
	`ALU_OPRN_WIDTH'h07 : OUT = OP1 | OP2; // 
	`ALU_OPRN_WIDTH'h08 : OUT = ~(OP1 | OP2); // nor
	`ALU_OPRN_WIDTH'h09 : OUT = OP1 < OP2; // set less than

	default: OUT = `DATA_WIDTH'hxxxxxxxx;
endcase
end

always @*
begin
if(OUT == 0) ZERO = 1;
else ZERO = 0;
end
endmodule

