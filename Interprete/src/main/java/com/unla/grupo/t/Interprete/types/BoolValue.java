package com.unla.grupo.t.Interprete.types;

public class BoolValue extends Value {
	private boolean value;
	
	public BoolValue(boolean value) {
		this.value = value;
	}
	
	@Override
	public Value plus(Value other) {
		throw new RuntimeException(
			"Incompatible operation for bool data type"
		);
	}

	@Override
	public Value minus(Value other) {
		throw new RuntimeException(
			"Incompatible operation for bool data type"
		);
	}

	@Override
	public Value multiplication(Value other) {
		throw new RuntimeException(
			"Incompatible operation for bool data type"
		);
	}

	@Override
	public Value division(Value other) {
		throw new RuntimeException(
			"Incompatible operation for bool data type"
		);
	}
	
	@Override
	public Value and(Value other) {
		if (other instanceof BoolValue) {
			return new BoolValue(
				this.value && ((BoolValue) other).getValue()
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}

	@Override
	public Value or(Value other) {
		if (other instanceof BoolValue) {
			return new BoolValue(
				this.value || ((BoolValue) other).getValue()
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}
	
	@Override
	public Value gt(Value other) {
		throw new RuntimeException(
			"Incompatible operation for bool data type"
		);
	}

	@Override
	public Value lt(Value other) {
		throw new RuntimeException(
			"Incompatible operation for bool data type"
		);
	}

	@Override
	public Value geq(Value other) {
		throw new RuntimeException(
			"Incompatible operation for bool data type"
		);
	}

	@Override
	public Value leq(Value other) {
		throw new RuntimeException(
			"Incompatible operation for bool data type"
		);
	}

	@Override
	public Value eq(Value other) {
		throw new RuntimeException(
			"Incompatible operation for bool data type"
		);
	}

	@Override
	public Value neq(Value other) {
		throw new RuntimeException(
			"Incompatible operation for bool data type"
		);
	}

	public boolean getValue() {
		return this.value;
	}
	
	public void setValue(boolean value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return Boolean.toString(this.value);
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof BoolValue) {
			return this.value == (((BoolValue) other).getValue());
		}
		
		return false;
	}
}
