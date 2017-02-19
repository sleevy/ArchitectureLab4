package com.example.cameronrhodes.lab4alu;

public class ALU {
    private FullAdder[] adders;
    private XOrGate[] opGates;

    private boolean[] S;

//    private boolean subtract;
    private int opBits = 3;

    private boolean[] op = new boolean[opBits];

    private boolean overflow;

    private int bits; //needed?

    private boolean[] A, B;

//	private final String NEGATIVE = "-", POSITIVE = "+", ZERO = "";
//	private String sign = POSITIVE;

    public ALU(int nBits) {
        bits = nBits;
        adders = new FullAdder[bits];
        opGates = new XOrGate[bits];
        S = new boolean[bits];
        A = new boolean[bits];
        B = new boolean[bits];
        for (int i = 0; i < bits; i++) {
            adders[i] = new FullAdder();
            opGates[i] = new XOrGate();
        }
    }

    public void setInputs(boolean[] sA, boolean[] sB, boolean[] sOp) {
//		if(A.length != bits || B.length != bits) {
//			System.out.println("Error. Length of inputs must be equal to number of bits");
//			//TODO: set inputs to zero if array not long enough
//			return;
//		}
        for (int i = 0; i < bits; i++) {
            if (i < sA.length) {
                A[i] = sA[i];
            } else {
                A[i] = false;
            }

            if (i < sB.length) {
                B[i] = sB[i];
            } else {
                B[i] = false;
            }
        }
//        subtract = op;
        for(int i = 0; i < op.length; i++) {
            if(i < sOp.length) op[i] = sOp[i];
            else op[i] = false;
        }
    }

    public void execute() {

        if(op[0]) {//1XX
            if(op[1]) {//11X
                if(op[2]) { //111
                    negate();
                } else { //110
                    set();
                }
            } else { //10
                if(op[2]) {//101
                    clear();
                } else { //100
                    addSubtract(true);
                }
            }
        } else { //0XX
            if(op[1]) { //01X
                if(op[2]) {//011
                    addSubtract(false);
                } else {//010
                    xor();
                }
            } else { //00X
                if(op[2]) {//001
                    or();
                } else {//000
                    and();
                }
            }
        }


//		int iB = 0;
//		int iA = 0;
//		for(int i = 0; i < bits; i++) {
//			iA += ((A[i]) ? Math.pow(2, i):0);
//			iB += ((B[i]) ? Math.pow(2, i):0);
//		}
//		if(iA < iB) {
//			overflow = true;
//		}
//		if((iA < iB) && subtract) {
//			sign = NEGATIVE;
//		} else if (iA == iB) {
//			sign = ZERO;
//		} else {
//			sign = POSITIVE;
//		}
        //if subtract and B>A
//		boolean bLarger = false;

//		if(subtract) {
//			overflow = !overflow;
//		}
//		if((iB > iA) && subtract) {
//			overflow = !overflow;
//			for(int i = 0; i < bits; i++) {
//				S[i] = !S[i];
//			}
//			
//		}

    }

//	public String getSign() {
//		return sign;
//	}

    private void and() {
        AndGate aOp = new AndGate();
        for(int i = 0; i < bits; i++) {
            aOp.setInputs(A[i],B[i]);
            aOp.execute();
            S[i] = aOp.getOutput();
        }
        overflow = false;
    }

    private void xor() {
        XOrGate xGate = new XOrGate();
        for(int i = 0; i < bits; i++) {
            xGate.setInputs(A[i],B[i]);
            xGate.execute();
            S[i] = xGate.getOutput();
        }
        overflow = false;
    }

    private void or() {
        OrGate oGate = new OrGate();
        for(int i = 0; i < bits; i++) {
            oGate.setInputs(A[i],B[i]);
            oGate.execute();
            S[i] = oGate.getOutput();
        }
        overflow = false;
    }

    private void clear() {
        for(int i = 0; i < bits; i++) {
            A[i] = false;
            B[i] = false;
            S[i] = false;
        }
        overflow = false;
    }

    private void set() {
        for(int i = 0; i < bits; i++) {
            A[i] = true;
            B[i] = true;
            S[i] = true;
        }
    }

    private void negate() {
        for(int i = 0; i < bits; i++) {
            A[i] = !A[i];
            B[i] = !B[i];
            S[i] = !S[i];
        }
    }

    private void addSubtract(boolean subtract) {
        for (int i = 0; i < bits; i++) {
            opGates[i].setInputs(B[i], subtract);
            opGates[i].execute();

            //DEBUG
//			opGates[i].print();

            adders[i].setInputs(A[i], opGates[i].getOutput(), (i == 0) ? subtract : adders[i - 1].getCarry());
            adders[i].execute();

            //DEBUG
//			adders[i].print();

            S[i] = adders[i].getSum();
        }
        overflow = adders[bits - 1].getCarry();
    }

    public boolean[] getSums() {
        return S;
    }

    public boolean getOverflow() {
        return overflow;
    }

    public boolean[] getOp() {
//        return subtract;
        return op;
    }

    public void print() {
        String out = "";

        System.out.println(out);
    }
}
