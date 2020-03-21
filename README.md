## Transliterador do MIPS
Esse é um projeto que decodifica comandos em linguagem de montagem para a linguagem 
de máquina do MIPS, sendo utilizado o Java para a elaboração do projeto.
 Instruções que podem ser realizdas:
- Aritméticas: add, addi sub, mult, div, neg. 
- Lógicas: and, andi, or, ori, xor, nor, slt, slti. 
- Deslocamento Bit a Bit: sll, srl. 
- Acesso à Memória: lw, sw. 
- Desvio Condicional: beq, bne. 
- Desvio Incondicional: j, jr, jal. 
- Outras: nop.
Os comandos são lidos em um arquivo de entrada em linguagem de montagem por linha 
e as intruções de máquina são escritas em um arquivo de saida.

## Como executar
# Plataformas Testadas
Ubuntu 18.04 e posteriores
Debian Testing/Sid
Pop OS 19.10
Linux Mint 19.1  e posteriores

<code> 
# sudo apt install unzip default-jre default-jdk &nbsp;
# unzip mips32-decode-master &nbsp;
# cd mips32-decode-master/ &nbsp;
# sh install.sh &nbsp;
# java -jar mips32-decode.jar input.txt output.txt &nbsp;
</code>
