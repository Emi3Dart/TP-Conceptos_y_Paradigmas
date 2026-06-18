package com.unla.grupo.t.Interprete.ast;

import java.util.Map;

public class Constant implements ASTNode {
	private Object constant;
	
	public Constant(Object constant) {
		super();
		this.constant = constant;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable) {
		// TODO Auto-generated method stub
		return constant;
	}

}
