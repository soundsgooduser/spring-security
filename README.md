# spring-security

# Run 
GET http://localhost:8080/users/info
Authorization Basic dXNlcm1lOnBhc3N3b3JkbWU=

# Docker
1. docker -v
2. docker build -f Dockerfile -t docker-spring-security
3. docker build --no-cache -f Dockerfile -t docker-spring-security .
4. docker images
5. docker run -p 8085:8085 docker-spring-security