package com.example.cameronrhodes.lab3alu;

public class ALU {
    private FullAdder[] adders;
    private XOrGate[] opGates;

    private boolean[] sums;

    private boolean subtract;

    private boolean overflow;

    private int bits; //needed?

    private boolean[] A, B;

//	private final String NEGATIVE = "-", POSITIVE = "+", ZERO = "";
//	private String sign = POSITIVE;

    public ALU(int nBits) {
        bits = nBits;
        adders = new FullAdder[bits];
        opGates = new XOrGate[bits];
        sums = new boolean[bits];
        A = new boolean[bits];
        B = new boolean[bits];
        for (int i = 0; i < bits; i++) {
            adders[i] = new FullAdder();
            opGates[i] = new XOrGate();
        }
    }

    public void setInputs(boolean[] sA, boolean[] sB, boolean op) {
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
        subtract = op;

    }

    public void execute() {


        for (int i = 0; i < bits; i++) {
            opGates[i].setInputs(B[i], subtract);
            opGates[i].execute();

            //DEBUG
//			opGates[i].print();

            adders[i].setInputs(A[i], opGates[i].getOutput(), (i == 0) ? subtract : adders[i - 1].getCarry());
            adders[i].execute();

            //DEBUG
//			adders[i].print();

            sums[i] = adders[i].getSum();
        }
        overflow = adders[bits - 1].getCarry();

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
//				sums[i] = !sums[i];
//			}
//			
//		}

    }

//	public String getSign() {
//		return sign;
//	}

    public boolean[] getSums() {
        return sums;
    }

    public boolean getOverflow() {
        return overflow;
    }

    public boolean getOp() {
        return subtract;
    }

    public void print() {
        String out = "";

        System.out.println(out);
    }
}
