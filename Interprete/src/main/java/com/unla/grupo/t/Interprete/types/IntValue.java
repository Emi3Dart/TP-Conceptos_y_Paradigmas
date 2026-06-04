package com.unla.grupo.t.Interprete.types;

public class IntValue extends Value {

	private int value;
	
	public IntValue(int value) {
		this.value = value;
	}
	
	@Override
	public Value plus(Value other) {
		if (other instanceof IntValue) {			
			return new IntValue(
				this.value + ((IntValue) other).getValue()
			);
		}
		
		if (other instanceof FloatValue) {			
			return new FloatValue(
				this.value + ((FloatValue) other).getValue()
			);
		}
		
		if (other instanceof StringValue) {			
			return new StringValue(
				this.value + ((StringValue) other).getValue()
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}

	@Override
	public Value minus(Value other) {
		if (other instanceof IntValue) {			
			return new IntValue(
				this.value - ((IntValue) other).getValue()
			);
		}
		
		if (other instanceof FloatValue) {			
			return new FloatValue(
				this.value - ((FloatValue) other).getValue()
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}

	@Override
	public Value multiplication(Value other) {
		if (other instanceof IntValue) {			
			return new IntValue(
				this.value * ((IntValue) other).getValue()
			);
		}
		
		if (other instanceof FloatValue) {			
			return new FloatValue(
				this.value * ((FloatValue) other).getValue()
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}

	@Override
	public Value division(Value other) {
		if (other instanceof IntValue) {
			int otherValue = ((IntValue) other).getValue();
			
			if (otherValue == 0) {
				throw new RuntimeException(
					"Invalid operation. Cannot divide by 0"
				);
			}
			
			return new IntValue(
				this.value / otherValue 
			);
		}
		
		if (other instanceof FloatValue) {
			float otherValue = ((FloatValue) other).getValue();
			
			if (otherValue == 0f) {
				throw new RuntimeException(
					"Invalid operation. Cannot divide by 0"
				);
			}
			
			return new FloatValue(
				this.value / otherValue 
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}
	
	@Override
	public Value and(Value other) {
		throw new RuntimeException(
			"Incompatible operation for int data type"
		);
	}

	@Override
	public Value or(Value other) {
		throw new RuntimeException(
			"Incompatible operation for int data type"
		);
	}
	
	@Override
	public Value gt(Value other) {
		if (other instanceof IntValue) {
			return new BoolValue(
				this.value > ((IntValue) other).getValue()
			);
		}
		
		if (other instanceof FloatValue) {
			return new BoolValue(
				this.value > ((FloatValue) other).getValue()
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}

	@Override
	public Value lt(Value other) {
		if (other instanceof IntValue) {
			return new BoolValue(
				this.value < ((IntValue) other).getValue()
			);
		}
		
		if (other instanceof FloatValue) {
			return new BoolValue(
				this.value < ((FloatValue) other).getValue()
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}

	@Override
	public Value geq(Value other) {
		if (other instanceof IntValue) {
			return new BoolValue(
				this.value >= ((IntValue) other).getValue()
			);
		}
		
		if (other instanceof FloatValue) {
			return new BoolValue(
				this.value >= ((FloatValue) other).getValue()
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}

	@Override
	public Value leq(Value other) {
		if (other instanceof IntValue) {
			return new BoolValue(
				this.value <= ((IntValue) other).getValue()
			);
		}
		
		if (other instanceof FloatValue) {
			return new BoolValue(
				this.value <= ((FloatValue) other).getValue()
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}

	@Override
	public Value eq(Value other) {
		if (other instanceof IntValue) {
			return new BoolValue(
				this.value == ((IntValue) other).getValue()
			);
		}
		
		if (other instanceof FloatValue) {
			return new BoolValue(
				this.value == ((FloatValue) other).getValue()
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}

	@Override
	public Value neq(Value other) {
		if (other instanceof IntValue) {
			return new BoolValue(
				this.value != ((IntValue) other).getValue()
			);
		}
		
		if (other instanceof FloatValue) {
			return new BoolValue(
				this.value != ((FloatValue) other).getValue()
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return Integer.toString(this.value);
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof IntValue) {
			return this.value == ((IntValue) other).getValue();
		}
		
		if (other instanceof FloatValue) {
			return this.value == ((FloatValue) other).getValue();
		}
		
		return false;
	}
}
