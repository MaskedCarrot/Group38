//: version "2.1"
//: property encoding = "utf-8"
//: property locale = "en"
//: property prefix = "_GG"
//: property title = "pro.v"
//: property timingViolationMode = 2
//: property initTime = "0 ns"

`timescale 1ns/1ns

//: /netlistBegin main
module main;    //: root_module
//: enddecls


endmodule
//: /netlistEnd

//: /netlistBegin Type3_Unit
module Type3_Unit(w3, w2, w1, w0);
//: interface  /sz:(40, 40) /bd:[ ] /pd: 0 /pi: 0 /pe: 1 /pp: 1
input w0;    //: /sn:0 {0}(211,184)(226,184){1}
input [7:0] w3;    //: /sn:0 {0}(#:211,395)(229,395){1}
input w1;    //: /sn:0 {0}(211,246)(226,246){1}
input [7:0] w2;    //: /sn:0 {0}(#:211,315)(226,315){1}
//: enddecls

  //: IN g3 (w3) @(209,395) /sn:0 /w:[ 0 ]
  //: IN g2 (w2) @(209,315) /sn:0 /w:[ 0 ]
  //: IN g1 (w1) @(209,246) /sn:0 /w:[ 0 ]
  //: IN g0 (w0) @(209,184) /sn:0 /w:[ 0 ]

endmodule
//: /netlistEnd

//: /netlistBegin RAM
module RAM();
//: interface  /sz:(40, 40) /bd:[ ] /pd: 0 /pi: 0 /pe: 1 /pp: 1
//: enddecls


endmodule
//: /netlistEnd

//: /netlistBegin Register_File
module Register_File(R_Signal2, Input_Data, R_Signal1, Output1, Write_Register_Selection, Output2);
//: interface  /sz:(40, 40) /bd:[ ] /pd: 0 /pi: 0 /pe: 1 /pp: 1
input [1:0] R_Signal1;    //: /sn:0 {0}(#:-72,-46)(535,-46)(535,55){1}
reg w7;    //: /sn:0 {0}(-141,42)(180,42)(180,160){1}
//: {2}(178,162)(151,162)(151,183)(132,183){3}
//: {4}(180,164)(180,290)(134,290){5}
input [7:0] Input_Data;    //: /sn:0 {0}(93,178)(93,168)(#:4,168){1}
//: {2}(2,166)(2,-8)(#:-54,-8){3}
//: {4}(2,170)(2,267)(95,267)(95,285){5}
input [1:0] Write_Register_Selection;    //: /sn:0 {0}(#:-133,125)(-102,125)(-102,215)(-79,215){1}
input [1:0] R_Signal2;    //: /sn:0 {0}(#:-64,-78)(552,-78)(552,-86)(624,-86)(624,-37){1}
supply0 w12;    //: /sn:0 {0}(134,300)(283,300)(283,294){1}
//: {2}(285,292)(339,292)(339,447){3}
//: {4}(283,290)(283,214)(263,214)(263,193)(132,193){5}
output [7:0] Output2;    //: /sn:0 {0}(#:800,252)(761,252)(761,252)(752,252){1}
//: {2}(748,252)(#:140,252){3}
//: {4}(750,254)(750,380)(#:148,380){5}
output [7:0] Output1;    //: /sn:0 {0}(#:138,336)(709,336)(709,228){1}
//: {2}(711,226)(798,226){3}
//: {4}(707,226)(#:135,226){5}
wire w6;    //: /sn:0 {0}(496,68)(511,68){1}
wire w39;    //: /sn:0 {0}(606,-8)(606,373)(140,373)(140,379){1}
wire w4;    //: /sn:0 {0}(-50,209)(-20,209){1}
wire w36;    //: /sn:0 {0}(130,331)(130,323)(161,323)(161,419)(517,419)(517,84){1}
wire w22;    //: /sn:0 {0}(553,84)(553,90){1}
wire w0;    //: /sn:0 {0}(630,-8)(630,-1){1}
wire w3;    //: /sn:0 {0}(642,-8)(642,2){1}
wire w30;    //: /sn:0 {0}(127,221)(127,213)(158,213)(158,309)(529,309)(529,84){1}
wire w19;    //: /sn:0 {0}(58,295)(-9,295)(-9,233)(-50,233){1}
wire [7:0] w10;    //: /sn:0 {0}(#:119,226)(93,226)(93,225){1}
//: {2}(93,221)(#:93,199){3}
//: {4}(91,223)(#:90,223)(90,252)(124,252){5}
wire w21;    //: /sn:0 {0}(-66,252)(-66,237){1}
wire w1;    //: /sn:0 {0}(132,247)(132,242)(618,242)(618,-8){1}
wire w8;    //: /sn:0 {0}(585,-24)(600,-24){1}
wire w14;    //: /sn:0 {0}(56,188)(19,188)(19,221)(-50,221){1}
wire [7:0] w15;    //: /sn:0 {0}(#:122,336)(97,336){1}
//: {2}(95,334)(#:95,306){3}
//: {4}(#:95,338)(95,384)(132,384){5}
wire w5;    //: /sn:0 {0}(541,84)(541,95){1}
wire w9;    //: /sn:0 {0}(-50,197)(-19,197){1}
//: enddecls

  _GGDECODER4 #(6, 6) g4 (.I(Write_Register_Selection), .E(w21), .Z0(w19), .Z1(w14), .Z2(w4), .Z3(w9));   //: @(-66,215) /sn:0 /R:1 /w:[ 1 1 1 1 0 0 ] /ss:0 /do:0
  _GGREG8 #(10, 10, 20) g3 (.Q(w15), .D(Input_Data), .EN(w12), .CLR(w7), .CK(w19));   //: @(95,295) /sn:0 /w:[ 3 5 0 5 0 ]
  _GGBUFIF8 #(4, 6) g17 (.Z(Output1), .I(w10), .E(w30));   //: @(125,226) /sn:0 /w:[ 5 0 0 ]
  _GGREG8 #(10, 10, 20) g2 (.Q(w10), .D(Input_Data), .EN(w12), .CLR(w7), .CK(w14));   //: @(93,188) /sn:0 /w:[ 3 0 5 3 0 ]
  //: joint g23 (w10) @(93, 223) /w:[ -1 2 4 1 ]
  //: IN g30 (R_Signal2) @(-66,-78) /sn:0 /w:[ 0 ]
  //: joint g39 (Output1) @(709, 226) /w:[ 2 -1 4 1 ]
  //: joint g24 (w15) @(95, 336) /w:[ 1 2 -1 4 ]
  //: GROUND g1 (w12) @(339,453) /sn:0 /w:[ 3 ]
  _GGBUFIF8 #(4, 6) g18 (.Z(Output2), .I(w10), .E(w1));   //: @(130,252) /sn:0 /w:[ 3 5 0 ]
  //: SWITCH g08 (w7) @(-158,42) /sn:0 /w:[ 0 ] /st:0 /dn:1
  //: IN g25 (Input_Data) @(-56,-8) /sn:0 /w:[ 3 ]
  //: joint g7 (w7) @(180, 162) /w:[ -1 1 2 4 ]
  //: IN g31 (R_Signal1) @(-74,-46) /sn:0 /w:[ 0 ]
  //: OUT g36 (Output1) @(795,226) /sn:0 /w:[ 3 ]
  _GGDECODER4 #(6, 6) g33 (.I(R_Signal2), .E(w8), .Z0(w39), .Z1(w1), .Z2(w0), .Z3(w3));   //: @(624,-24) /sn:0 /w:[ 1 1 0 1 0 0 ] /ss:0 /do:0
  //: IN g42 (Write_Register_Selection) @(-135,125) /sn:0 /w:[ 0 ]
  //: joint g40 (Output2) @(750, 252) /w:[ 1 -1 2 4 ]
  //: joint g28 (Input_Data) @(2, 168) /w:[ 1 2 -1 4 ]
  _GGBUFIF8 #(4, 6) g19 (.Z(Output1), .I(w15), .E(w36));   //: @(128,336) /sn:0 /w:[ 0 0 0 ]
  _GGDECODER4 #(6, 6) g32 (.I(R_Signal1), .E(w6), .Z0(w36), .Z1(w30), .Z2(w5), .Z3(w22));   //: @(535,68) /sn:0 /w:[ 1 1 1 1 0 0 ] /ss:0 /do:0
  _GGBUFIF8 #(4, 6) g20 (.Z(Output2), .I(w15), .E(w39));   //: @(138,384) /sn:0 /w:[ 5 5 1 ]
  //: joint g0 (w12) @(283, 292) /w:[ 2 4 -1 1 ]
  //: OUT g37 (Output2) @(797,252) /sn:0 /w:[ 0 ]

endmodule
//: /netlistEnd

//: /netlistBegin Type4_Unit
module Type4_Unit(From_Register, Output1, Unconditional_Br_Selection, Output0, NOT_Selection);
//: interface  /sz:(40, 40) /bd:[ ] /pd: 0 /pi: 0 /pe: 1 /pp: 1
input NOT_Selection;    //: /sn:0 {0}(76,51)(582,51)(582,93){1}
output [7:0] Output0;    //: /sn:0 {0}(#:625,212)(1048,212)(1048,211)(1053,211){1}
input [7:0] From_Register;    //: /sn:0 {0}(#:609,212)(603,212)(603,365)(303,365){1}
//: {2}(301,363)(301,98)(574,98){3}
//: {4}(299,365)(#:88,365){5}
input Unconditional_Br_Selection;    //: /sn:0 {0}(88,183)(617,183)(617,207){1}
output [7:0] Output1;    //: /sn:0 {0}(#:825,101)(1012,101)(1012,100)(1199,100){1}
wire [7:0] w7;    //: /sn:0 {0}(#:590,98)(717,98){1}
//: {2}(721,98)(804,98){3}
//: {4}(719,100)(719,128)(778,128)(778,103)(804,103){5}
//: enddecls

  _GGBUFIF8 #(4, 6) g4 (.Z(Output0), .I(From_Register), .E(Unconditional_Br_Selection));   //: @(615,212) /sn:0 /w:[ 0 0 1 ]
  //: joint g8 (w7) @(719, 98) /w:[ 2 -1 1 4 ]
  //: IN g3 (From_Register) @(86,365) /sn:0 /w:[ 5 ]
  //: OUT g2 (Output1) @(1196,99) /sn:0 /w:[ 1 ]
  //: IN g1 (Unconditional_Br_Selection) @(86,183) /sn:0 /w:[ 0 ]
  //: OUT g10 (Output0) @(1050,211) /sn:0 /w:[ 1 ]
  //: joint g6 (From_Register) @(301, 365) /w:[ 1 2 4 -1 ]
  //: comment g9 @(995,230) /sn:0
  //: /line:"Address which goes to PC"
  //: /end
  _GGNAND2x8 #(4) g7 (.I0(w7), .I1(w7), .Z(Output1));   //: @(815,101) /sn:0 /w:[ 3 5 0 ]
  //: comment g11 @(1149,114) /sn:0
  //: /line:"Result of NOT Operation"
  //: /end
  _GGBUFIF8 #(4, 6) g5 (.Z(w7), .I(From_Register), .E(NOT_Selection));   //: @(580,98) /sn:0 /w:[ 0 3 1 ]
  //: IN g0 (NOT_Selection) @(74,51) /sn:0 /w:[ 0 ]

endmodule
//: /netlistEnd

