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
		Object oldValue = symbolTable.get(name);
		if (oldValue == null)
			throw new RuntimeException("Variable is not defined: " + name);

		Object value = expression.execute(symbolTable);

		if (oldValue instanceof Integer && !(value instanceof Integer))
			throw new RuntimeException("Type mismatch. Expected int but got " + value.getClass().getSimpleName());
		if (oldValue instanceof Float && !(value instanceof Float))
			throw new RuntimeException("Type mismatch. Expected float but got " + value.getClass().getSimpleName());
		if (oldValue instanceof String && !(value instanceof String))
			throw new RuntimeException("Type mismatch. Expected string but got " + value.getClass().getSimpleName());
		if (oldValue instanceof Boolean && !(value instanceof Boolean))
			throw new RuntimeException("Type mismatch. Expected bool but got " + value.getClass().getSimpleName());

		symbolTable.put(name, value);
		return null;
	}
}
