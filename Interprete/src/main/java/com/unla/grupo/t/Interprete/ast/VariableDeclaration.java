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
		if (symbolTable.containsKey(name))
			throw new RuntimeException("Variable already defined: " + name);

		Object defaultValue;
		switch (type) {
			case "int":    defaultValue = 0;       break;
			case "float":  defaultValue = 0.0f;     break;
			case "string": defaultValue = "";       break;
			case "bool":   defaultValue = false;    break;
			default: throw new RuntimeException("Unknown type: " + type);
		}
		symbolTable.put(name, defaultValue);
		return null;
	}
}
