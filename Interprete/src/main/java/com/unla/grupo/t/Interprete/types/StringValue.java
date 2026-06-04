package com.unla.grupo.t.Interprete.types;

public class StringValue extends Value {
	
	private String value;
	
	public StringValue(String value) {
		this.value = value.replace("\"", "");
	}
	
	@Override
	public Value plus(Value other) {
		if (other instanceof StringValue) {
			return new StringValue(
				this.value + ((StringValue) other).getValue()
			);
		}
		
		if (other instanceof IntValue) {
			return new StringValue(
				this.value + ((IntValue) other).getValue()
			);
		}
		
		if (other instanceof FloatValue) {
			return new StringValue(
				this.value + ((FloatValue) other).getValue()
			);
		}
		
		if (other instanceof BoolValue) {
			return new StringValue(
				this.value + ((BoolValue) other).getValue()
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}

	@Override
	public Value minus(Value other) {
		throw new RuntimeException(
			"Incompatible operation for string data type"
		);
	}

	@Override
	public Value multiplication(Value other) {
		throw new RuntimeException(
			"Incompatible operation for string data type"
		);
	}

	@Override
	public Value division(Value other) {
		throw new RuntimeException(
			"Incompatible operation for string data type"
		);
	}
	
	@Override
	public Value and(Value other) {
		throw new RuntimeException(
			"Incompatible operation for string data type"
		);
	}

	@Override
	public Value or(Value other) {
		throw new RuntimeException(
			"Incompatible operation for string data type"
		);
	}
	
	@Override
	public Value gt(Value other) {
		if (other instanceof StringValue) {
			return new BoolValue(
				this.value.length() > ((StringValue) other).getValue().length()
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}

	@Override
	public Value lt(Value other) {
		if (other instanceof StringValue) {
			return new BoolValue(
				this.value.length() < ((StringValue) other).getValue().length()
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}

	@Override
	public Value geq(Value other) {
		if (other instanceof StringValue) {
			return new BoolValue(
				this.value.length() >= ((StringValue) other).getValue().length()
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}

	@Override
	public Value leq(Value other) {
		if (other instanceof StringValue) {
			return new BoolValue(
				this.value.length() <= ((StringValue) other).getValue().length()
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}

	@Override
	public Value eq(Value other) {
		if (other instanceof StringValue) {
			return new BoolValue(
				this.value.length() == ((StringValue) other).getValue().length()
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}

	@Override
	public Value neq(Value other) {
		if (other instanceof StringValue) {
			return new BoolValue(
				this.value.length() != ((StringValue) other).getValue().length()
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}

	public String getValue() {
		return this.value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof StringValue) {
			return this.value.equals(((StringValue) other).getValue());
		}
		
		return false;
	}
}
