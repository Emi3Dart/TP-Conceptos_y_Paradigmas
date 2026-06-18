package com.unla.grupo.t.Interprete.ast;

import java.util.Map;

public class VariableDeclaration implements ASTNode {
	private String name;
	private String type;
	
	

	public VariableDeclaration(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}



	@Override
	public Object execute(Map<String, Object> symbolTable) {
		symbolTable.put(name, new Object());
		return null;
	}

}
