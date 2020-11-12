Feature: Descrição do que será testado nessa feature. 
				 Nesse modelo temos um serviço de cadastro de usuários, e faremos os testes de crud básicos

  Scenario Outline: Descrição deste cenário de teste. 
  									Nesse cenário testaremos a criação de usuários
    Given Irei executar um <type>
    And Com o id <id>, o nome <name> e o email <email>
    When executar a chamada
    Then Devo verificar o resultado da execução

    Examples:
    |  type	|  id |  name             | email             |
    |  post | 0	  |  Usuário correto  | email@teste.com   |
    |  post | 0	  |  Usuário correto 2| email2@teste.com  |
    |  post | 0	  |  Usuário correto 3| email2@teste.com  |
    |  post | 0	  |  		              | email@teste.com   |
    |  post | 0 	|  Usuário correto  |     			        |
    
    
  Scenario Outline: Descrição deste cenário de teste. 
  									Nesse cenário testaremos a edição de usuários
    Given Irei executar um <type>
    And Com o id <id>, o nome <name> e o email <email>
    When executar a chamada
    Then Devo verificar o resultado da execução

    Examples:
    |  type	|  id  							 |  name           		 | email            |
    |  put  | 1					  			 |  Usuário correto edt| email@teste.com  |
    |  put  | 5  					  		 |  Usuário correto edt| email@teste.com  |
    |  put  | 1							  	 |  		           	 	 | email@teste.com  |
    |  put  |	1								   |  Usuário correto		 |      			      |
    
    
  Scenario Outline: Descrição deste cenário de teste. 
  									Nesse cenário testaremos a busca de usuários por id
    Given Irei executar um <type>
    And Buscando pelo id <id>
    When executar a chamada
    Then Devo verificar o resultado da consulta <mustFind>

    Examples:
    |  type	|  id     							 |	mustFind |
    |  get  |1											 |		true	 |
    |  get  |6											 |		false	 |
    
 	Scenario Outline: Descrição deste cenário de teste. 
  									Nesse cenário testaremos a listagem
    Given Irei executar um get
    And Buscando pelo id <id>
    When executar a chamada
    Then Devo verificar o resultado da listagem

    Examples:
    |  type	|  id     							 |	mustFind |
    |  get  | 											 |		true	 |