package com.unla.grupo.t.Interprete.types;

public abstract class Value {
	public abstract Value plus(Value other);
	public abstract Value minus(Value other);
	public abstract Value multiplication(Value other);
	public abstract Value division(Value other);
	
	public abstract Value and(Value other);
	public abstract Value or(Value other);
	
	public abstract Value gt(Value other);
	public abstract Value lt(Value other);
	public abstract Value geq(Value other);
	public abstract Value leq(Value other);
	public abstract Value eq(Value other);
	public abstract Value neq(Value other);
}
