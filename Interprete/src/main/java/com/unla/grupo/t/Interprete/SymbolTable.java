package com.unla.grupo.t.Interprete;

import java.util.HashMap;
import java.util.Map;

import com.unla.grupo.t.Interprete.types.IntValue;
import com.unla.grupo.t.Interprete.types.Value;

public class SymbolTable {
	private Map<String, Value> table;
	
	public SymbolTable() {
		this.table = new HashMap<String, Value>();
	}
	
	public void declare(String id, Value value) {
		Value oldValue = table.get(id);
		
		if (oldValue != null) {
			throw new RuntimeException(
				"Variable already defined"
			);
		}
		
		table.put(id, value);
	}
	
	public void assign(String id, Value value) {
		Value oldValue = table.get(id);
		
		if (oldValue == null) {
			throw new RuntimeException(
				"Variable is not defined"
			);
		}
		
		validateTypes(oldValue, value);
		
		table.put(id, value);
	}
	
	private void validateTypes(Value oldValue, Value newValue) {
		Class<?> oldValueClass = oldValue.getClass();
		Class<?> newValueClass = newValue.getClass();
		if (oldValueClass != newValueClass) {
			throw new RuntimeException(
		        "Type mismatch. Expected "
		        + oldValue.getClass().getSimpleName()
		        + " but got "
		        + newValue.getClass().getSimpleName()
		    );
		}
	}
	
	public Value get(String id) {
		return table.get(id);
	}
}
