package com.unla.grupo.t.Interprete.ast;

import java.util.Map;

public class VariableReference implements ASTNode {
	private String name;
	
	
	
	public VariableReference(String name) {
		super();
		this.name = name;
	}



	@Override
	public Object execute(Map<String, Object> symbolTable) {
		// TODO Auto-generated method stub
		return symbolTable.get(name);
	}

}
