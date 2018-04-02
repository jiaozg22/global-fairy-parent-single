package org.global.fairy.core;

public class VariableTypeInitTest {
	public int intVariaType ;
	public boolean booleanVariaType;
	public long longVariaType;
	public String strVariaType;
	public char charVariaType;
	public byte byteVariaType;
	public float flVariaType;
	public double douVariaype;
	public Object objVariaType;
	@Override
	public String toString() {
		return "VariableTypeInitTest [intVariaType=" + intVariaType
				+ ", booleanVariaType=" + booleanVariaType + ", longVariaType="
				+ longVariaType + ", strVariaType=" + strVariaType
				+ ", charVariaType=" + charVariaType + ", byteVariaType="
				+ byteVariaType + ", flVariaType=" + flVariaType
				+ ", douVariaype=" + douVariaype + ", objVariaType="
				+ objVariaType + "]";
	}
	
	public static void main(String[] args) {
		System.out.println(new VariableTypeInitTest());
	}

}
