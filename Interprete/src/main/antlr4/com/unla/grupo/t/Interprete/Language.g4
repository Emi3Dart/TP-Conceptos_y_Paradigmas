grammar Language;

@parser::header {
	import com.unla.grupo.t.Interprete.SymbolTable;
	import com.unla.grupo.t.Interprete.types.Value;
	import com.unla.grupo.t.Interprete.types.IntValue;
	import com.unla.grupo.t.Interprete.types.FloatValue;
	import com.unla.grupo.t.Interprete.types.StringValue;
	import com.unla.grupo.t.Interprete.types.BoolValue;
}

@parser::members {
	SymbolTable symbolTable = new SymbolTable();
}

main
:
	sentence*
;

sentence:
	variable_declaration
	|
	variable_da
	|
	variable_assignation
	|
	print
;

variable_declaration
:
	VAR ID SEMICOLON
	{
		if ($VAR.text.equals("int")) {
			symbolTable.declare($ID.text, new IntValue(0));
		}
		if ($VAR.text.equals("float")) {
			symbolTable.declare($ID.text, new FloatValue(0f));
		}
		if ($VAR.text.equals("string")) {
			symbolTable.declare($ID.text, new StringValue(""));
		}
		if ($VAR.text.equals("bool")) {
			symbolTable.declare($ID.text, new BoolValue(false));
		}
	}
;
variable_da
:
	VAR ID ASSIGN expression SEMICOLON
	{
		//validaciones:3
		if ($VAR.text.equals("int")) {
			if (!($expression.value instanceof IntValue)) {
				throw new RuntimeException("Expected int value");
			}
		} else if ($VAR.text.equals("float")) {
			if (!($expression.value instanceof FloatValue)) {
				throw new RuntimeException("Expected float value");
			}
		} else if ($VAR.text.equals("string")) {
			if (!($expression.value instanceof StringValue)) {
				throw new RuntimeException("Expected string value");
			}
		} else if ($VAR.text.equals("bool")) {
			if (!($expression.value instanceof BoolValue)) {
				throw new RuntimeException("Expected bool value");
			}
		} else {
			throw new RuntimeException("Unknown data type. Expected int, float, string or bool");
		}
		symbolTable.declare($ID.text, $expression.value);
	}
;
variable_assignation
:
	ID ASSIGN expression SEMICOLON
	{
		symbolTable.assign($ID.text, $expression.value);
	}
;
print
:
	PRINT expression SEMICOLON
	{System.out.println($expression.value);}
;
expression returns [Value value]
:
	t1=factor {$value = $t1.value;}
	(
		PLUS t2=factor {$value = $value.plus($t2.value);}
		|
		MINUS t2=factor {$value = $value.minus($t2.value);}
		|
		AND t2=factor {$value = $value.and($t2.value);}
		|
		OR t2=factor {$value = $value.or($t2.value);}
		|
		GT t2=factor {$value = $value.gt($t2.value);}
		|
		LT t2=factor {$value = $value.lt($t2.value);}
		|
		GEQ t2=factor {$value = $value.geq($t2.value);}
		|
		LEQ t2=factor {$value = $value.leq($t2.value);}
		|
		EQ t2=factor {$value = $value.eq($t2.value);}
		|
		NEQ t2=factor {$value = $value.neq($t2.value);}
	)*
;
factor returns [Value value]
:
	t1=term {$value = $t1.value;}
	(
		DIVISION t2=term {$value = $value.division($t2.value);}
		|
		MULTIPLICATION t2=term {$value = $value.multiplication($t2.value);}
	)*
;
term returns [Value value]
:
	ID {$value = symbolTable.get($ID.text);}
	|
	NUMBER {
		$value = new IntValue(
			Integer.parseInt($NUMBER.text)
		);
	}
	|
	FLOAT_NUMBER {
		$value = new FloatValue(
			Float.parseFloat($FLOAT_NUMBER.text)
		);
	}
	|
	STRING_VALUE {
		$value = new StringValue(
			$STRING_VALUE.text
		);
	}
	|
	BOOL_VALUE {
		$value = new BoolValue(
			Boolean.parseBoolean($BOOL_VALUE.text)
		);
	}
	|
	PARENTHESIS_OPEN expression PARENTHESIS_CLOSE {$value = $expression.value;}
;

VAR: INT | FLOAT | STRING | BOOL;
INT: 'int';
FLOAT: 'float';
STRING: 'string';
BOOL: 'bool';

PRINT: 'print';

ASSIGN: '=';

SEMICOLON: ';';

PLUS: '+';
MINUS: '-';
MULTIPLICATION: '*';
DIVISION: '/';

AND: '&&';
OR: '||';

GT: '>';
LT: '<';
GEQ: '>=';
LEQ: '<=';
EQ: '==';
NEQ: '!=';

PARENTHESIS_OPEN: '(';
PARENTHESIS_CLOSE: ')';

WS
:
	[ \t\r\n]+ -> skip
;

NUMBER: [0-9]+;
FLOAT_NUMBER: [0-9]+'.'[0-9]+;
STRING_VALUE: '"'~["]*'"';
BOOL_VALUE: 'false'|'true';

ID: [a-zA-Z_][a-zA-Z0-9_]*;