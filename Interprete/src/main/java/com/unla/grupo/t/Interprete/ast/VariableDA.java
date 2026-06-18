package com.unla.grupo.t.Interprete.ast;

import java.util.Map;

public class VariableDA implements ASTNode {
	private String name;
	private String type;
	private ASTNode expression;

	public VariableDA(String name, String type, ASTNode expression) {
		super();
		this.name = name;
		this.type = type;
		this.expression = expression;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable) {
		if (symbolTable.containsKey(name))
			throw new RuntimeException("Variable already defined: " + name);

		Object value = expression.execute(symbolTable);

		switch (type) {
			case "int":
				if (!(value instanceof Integer))
					throw new RuntimeException("Expected int value for variable " + name);
				break;
			case "float":
				if (!(value instanceof Float))
					throw new RuntimeException("Expected float value for variable " + name);
				break;
			case "string":
				if (!(value instanceof String))
					throw new RuntimeException("Expected string value for variable " + name);
				break;
			case "bool":
				if (!(value instanceof Boolean))
					throw new RuntimeException("Expected bool value for variable " + name);
				break;
			default:
				throw new RuntimeException("Unknown type: " + type);
		}

		symbolTable.put(name, value);
		return null;
	}
}
