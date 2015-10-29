 task print_instruction;
 input [`DATA_INDEX_LIMIT:0] inst;
 reg [5:0] opcode;
 reg [4:0] rs;
 reg [4:0] rt;
 reg [4:0] rd;
 reg [4:0] shamt;
 reg [5:0] funct;
 reg [15:0] immediate;
 reg [25:0] address;
 begin
// parse the instruction
// R-type
{opcode, rs, rt, rd, shamt, funct} = inst;

$write("@ %6dns -> [0X%08h] ", $time, inst);
 case(opcode)
// R-Type
6'h00 : begin
case(funct)
6'h20: $write("add r[%02d], r[%02d], r[%02d];", rs, rt, rd);
6'h22: $write("sub r[%02d], r[%02d], r[%02d];", rs, rt, rd);
6'h2c: $write("mul r[%02d], r[%02d], r[%02d];", rs, rt, rd);
6'h24: $write("and r[%02d], r[%02d], r[%02d];", rs, rt, rd);
3/5
6'h25: $write("or r[%02d], r[%02d], r[%02d];", rs, rt, rd);
6'h27: $write("nor r[%02d], r[%02d], r[%02d];", rs, rt, rd);
6'h2a: $write("slt r[%02d], r[%02d], r[%02d];", rs, rt, rd);
6'h00: $write("sll r[%02d], %2d, r[%02d];", rs, shamt, rd);
6'h02: $write("srl r[%02d], 0X%02h, r[%02d];", rs, shamt, rd);
default: $write("");
endcase
end