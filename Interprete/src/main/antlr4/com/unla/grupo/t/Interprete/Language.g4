grammar Language;

@parser::header {
	
	import java.util.List;
	import java.util.ArrayList;
	import java.util.Map;
	import java.util.HashMap;
	import com.unla.grupo.t.Interprete.ast.*;
}

@parser::members {
	SymbolTable symbolTable = new SymbolTable();
}

main
:
	{
		List<ASTNode> body = new ArrayList<ASTNode>();
		Map<String, Object> symbolTable = new HashMap<String, Object>();
	}
	(sentence {body.add($sentence.node);})*
	{
		for(ASTNode n : body){
			n.execute(symbolTable);
		}
	}
;

sentence returns [ASTNode node]:
	variable_declaration {$node = $variable_declaration.node;}
	|
	variable_da {$node = $variable_da.node;}
	|
	variable_assignation {$node = $variable_assignation.node;}
	|
	print {$node = $print.node;}
	|
	conditional {$node = $conditional.node;}
	|
	while_statement {$node = $while_statement.node;}
;

variable_declaration returns [ASTNode node]
:
	VAR ID SEMICOLON
	{
		$node = new VariableDeclaration($ID.text, $VAR.text);
	}
;
variable_da returns [ASTNode node]
:
	VAR ID ASSIGN expression SEMICOLON
	{
		$node = new VariableDA($ID.text, $VAR.text, $expression.node);
	}
;
variable_assignation returns [ASTNode node]
:
	ID ASSIGN expression SEMICOLON
	{
		$node = new VariableAssignation($ID.text, $expression.node);
	}
;
print returns [ASTNode node]
:
	PRINT expression SEMICOLON
	{$node = new Print($expression.node);}
;

conditional returns [ASTNode node] 
:
	
	IF PARENTHESIS_OPEN expression PARENTHESIS_CLOSE
	{
		List<ASTNode> body = new ArrayList<ASTNode>();
	}
	BRACKET_OPEN ( s1 = sentence {body.add($s1.node);})* BRACKET_CLOSE
	ELSE 
	{
		List<ASTNode> elseBody = new ArrayList<ASTNode>();
	}
	BRACKET_OPEN ( s2 = sentence {elseBody.add($s2.node);})* BRACKET_CLOSE
	{
		$node = new If($expression.node,body, elseBody);
	}
;

while_statement returns [ASTNode node] 
:
	
	WHILE PARENTHESIS_OPEN expression PARENTHESIS_CLOSE
	{
		List<ASTNode> body = new ArrayList<ASTNode>();
	}
	BRACKET_OPEN ( s = sentence {body.add($s.node);})* BRACKET_CLOSE
	{
		$node = new While($expression.node,body);
	}
;

expression returns [ASTNode node]
:
	t1=factor {$node = $t1.node;}
	(
		PLUS t2=factor {$node = new Addition($node, $t2.node);}
		|
		MINUS t2=factor {$node = new Subtraction($node, $t2.node);}
		|
		AND t2=factor {$node = new And($node, $t2.node);}
		|
		OR t2=factor {$node = new Or($node, $t2.node);}
		|
		GT t2=factor {$node = new Gt($node, $t2.node);}
		|
		LT t2=factor {$node = new Lt($node, $t2.node);}
		|
		GEQ t2=factor {$node = new Geq($node, $t2.node);}
		|
		LEQ t2=factor {$node = new Leq($node, $t2.node);}
		|
		EQ t2=factor {$node = new Eq($node, $t2.node);}
		|
		NEQ t2=factor {$node = new Neq($node, $t2.node);}
	)*
;
factor returns [ASTNode node]
:
	t1=term {$node = $t1.node;}
	(
		DIVISION t2=term {$node = new Division($node, $t2.node);}
		|
		MULTIPLICATION t2=term {$node = new Multiplication($node, $t2.node);}
	)*
;
term returns [ASTNode node]
:
	ID {$node = new VariableReference($ID.text);}
	|
	NUMBER {
		$node = new Constant(
			Integer.parseInt($NUMBER.text)
		);
	}
	|
	FLOAT_NUMBER {
		$node = new Constant(
			Float.parseFloat($FLOAT_NUMBER.text)
		);
	}
	|
	STRING_VALUE {
		String raw = $STRING_VALUE.text;
		$node = new Constant(raw.substring(1, raw.length() - 1));
	}
	|
	BOOL_VALUE {
		$node = new Constant(
			Boolean.parseBoolean($BOOL_VALUE.text)
		);
	}
	|
	PARENTHESIS_OPEN expression {$node = $expression.node;} PARENTHESIS_CLOSE 
;

VAR: INT | FLOAT | STRING | BOOL;
INT: 'int';
FLOAT: 'float';
STRING: 'string';
BOOL: 'bool';
IF: 'if';
ELSE: 'else';
WHILE: 'while';


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
BRACKET_OPEN: '{';
BRACKET_CLOSE: '}';

WS
:
	[ \t\r\n]+ -> skip
;

NUMBER: [0-9]+;
FLOAT_NUMBER: [0-9]+'.'[0-9]+;
STRING_VALUE: '"'~["]*'"';
BOOL_VALUE: 'false'|'true';

ID: [a-zA-Z_][a-zA-Z0-9_]*;

LINE_COMMENT: '//' ~[\r\n]* -> skip;
BLOCK_COMMENT: '/*' .*? '*/' -> skip;