# uploading-service

  db:
    image: postgres:9.4.11-alpine
    container_name: db
    volumes:
      - "db-data:/var/lib/postgresql/data"
    networks:
      - back-tier