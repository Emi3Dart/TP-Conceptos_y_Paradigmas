package com.unla.grupo.t.Interprete.ast;

import java.util.Map;

public class VariableAssignation implements ASTNode {
	private String name;
	private ASTNode expression;
	
	
	
	
	public VariableAssignation(String name, ASTNode expression) {
		super();
		this.name = name;
		this.expression = expression;
	}




	@Override
	public Object execute(Map<String, Object> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

}
