CREATE DATABASE Banco;

CREATE TABLE cuenta (
    id SERIAL PRIMARY KEY,
    numero_cuenta VARCHAR(20) UNIQUE NOT NULL,
    saldo NUMERIC(10, 2) NOT NULL,
    titular VARCHAR(255) NOT NULL,
    tipo_cuenta VARCHAR(20) NOT NULL
);

CREATE TABLE transaccion (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(20) NOT NULL,
    monto NUMERIC(10, 2) NOT NULL,
    fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    cuenta_id INT NOT NULL,
    FOREIGN KEY (cuenta_id) REFERENCES cuenta(id)
);

INSERT INTO cuenta (numero_cuenta, saldo, titular, tipo_cuenta) VALUES
('001', 5000.00, 'Juan Pérez', 'BASICA'),
('002', 15000.00, 'Ana López', 'PREMIUM');

INSERT INTO transaccion (tipo, monto, cuenta_id) VALUES
('DEPOSITO', 2000.00, 1),
('RETIRO', 500.00, 1),
('DEPOSITO', 5000.00, 2),
('RETIRO', 3000.00, 2);