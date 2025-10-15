
# API E-commerce (Spring Boot + MySQL) - Upgrade

Inclui:
- Testes unitários (JUnit + Mockito) e um teste de integração usando H2.
- Tratamento de erros com exceções customizadas e `GlobalExceptionHandler`.
- Upload de imagens para produtos (armazenamento local em `/uploads`).
- Perfis `dev` (padrão) e `prod` com `application-{profile}.properties`.
- `data.sql` que cria usuários e produtos de exemplo.

## Como rodar
1. Configure o MySQL e crie o banco `ecommerce_db`.
2. Ajuste `src/main/resources/application-dev.properties` se necessário.
3. `mvn clean package`
4. `mvn spring-boot:run` (ou `java -jar target/*.jar`)

## Testes
`mvn test` irá executar os testes unitários e de integração (o teste de integração usa H2 in-memory).
