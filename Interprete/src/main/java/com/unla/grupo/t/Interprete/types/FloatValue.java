package com.unla.grupo.t.Interprete.types;

public class FloatValue extends Value {

	private float value;
	
	public FloatValue(float value) {
		this.value = value;
	}
	
	@Override
	public Value plus(Value other) {
		if (other instanceof FloatValue) {			
			return new FloatValue(
				this.value + ((FloatValue) other).getValue()
			);
		}
		
		if (other instanceof IntValue) {			
			return new FloatValue(
				this.value + ((IntValue) other).getValue()
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
		if (other instanceof FloatValue) {			
			return new FloatValue(
				this.value - ((FloatValue) other).getValue()
			);
		}
		
		if (other instanceof IntValue) {			
			return new FloatValue(
				this.value - ((IntValue) other).getValue()
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}

	@Override
	public Value multiplication(Value other) {
		if (other instanceof FloatValue) {			
			return new FloatValue(
				this.value * ((FloatValue) other).getValue()
			);
		}
		
		if (other instanceof IntValue) {			
			return new FloatValue(
				this.value * ((IntValue) other).getValue()
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}

	@Override
	public Value division(Value other) {
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
		
		if (other instanceof IntValue) {
			int otherValue = ((IntValue) other).getValue();
			
			if (otherValue == 0) {
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
			"Incompatible operation for float data type"
		);
	}

	@Override
	public Value or(Value other) {
		throw new RuntimeException(
			"Incompatible operation for float data type"
		);
	}
	
	@Override
	public Value gt(Value other) {
		if (other instanceof FloatValue) {
			return new BoolValue(
				this.value > ((FloatValue) other).getValue()
			);
		}
		
		if (other instanceof IntValue) {
			return new BoolValue(
				this.value > ((IntValue) other).getValue()
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}

	@Override
	public Value lt(Value other) {
		if (other instanceof FloatValue) {
			return new BoolValue(
				this.value < ((FloatValue) other).getValue()
			);
		}
		
		if (other instanceof IntValue) {
			return new BoolValue(
				this.value < ((IntValue) other).getValue()
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}

	@Override
	public Value geq(Value other) {
		if (other instanceof FloatValue) {
			return new BoolValue(
				this.value >= ((FloatValue) other).getValue()
			);
		}
		
		if (other instanceof IntValue) {
			return new BoolValue(
				this.value >= ((IntValue) other).getValue()
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}

	@Override
	public Value leq(Value other) {
		if (other instanceof FloatValue) {
			return new BoolValue(
				this.value <= ((FloatValue) other).getValue()
			);
		}
		
		if (other instanceof IntValue) {
			return new BoolValue(
				this.value <= ((IntValue) other).getValue()
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}

	@Override
	public Value eq(Value other) {
		if (other instanceof FloatValue) {
			return new BoolValue(
				this.value == ((FloatValue) other).getValue()
			);
		}
		
		if (other instanceof IntValue) {
			return new BoolValue(
				this.value == ((IntValue) other).getValue()
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}

	@Override
	public Value neq(Value other) {
		if (other instanceof FloatValue) {
			return new BoolValue(
				this.value != ((FloatValue) other).getValue()
			);
		}
		
		if (other instanceof IntValue) {
			return new BoolValue(
				this.value != ((IntValue) other).getValue()
			);
		}
		
		throw new RuntimeException(
			"Incompatible data types"
		);
	}
	
	public float getValue() {
		return this.value;
	}
	
	public void setValue(float value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return Float.toString(this.value);
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof FloatValue) {
			return this.value == ((FloatValue) other).getValue();
		}
		
		if (other instanceof IntValue) {
			return this.value == ((IntValue) other).getValue();
		}
		
		return false;
	}
}
