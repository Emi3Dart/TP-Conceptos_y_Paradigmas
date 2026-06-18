package com.unla.grupo.t.Interprete.ast;

import java.util.Map;

public class Leq implements ASTNode {
	private ASTNode operand1;
	private ASTNode operand2;
	
	
	public Leq(ASTNode operand1, ASTNode operand2) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
	}


	@Override
	public Object execute(Map<String, Object> symbolTable) {
		Object v1 = operand1.execute(symbolTable);
		Object v2 = operand2.execute(symbolTable);
		
		if(v1 instanceof Integer && v2 instanceof Integer) {
			return (Integer) v1 <= (Integer) v2;
		}if(v1 instanceof Float || v2 instanceof Float) {
			return ((Number) v1).floatValue() <= ((Number) v2).floatValue();
		}
		throw new RuntimeException("Incompatible types for <=");
	}

}
