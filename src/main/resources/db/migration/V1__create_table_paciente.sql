CREATE TABLE paciente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50),
    sobrenome  VARCHAR (50),
    dataNascimento DATE ,
    cpf VARCHAR (15),
    email VARCHAR (100)
)