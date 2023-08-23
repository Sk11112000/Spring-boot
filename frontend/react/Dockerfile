FROM node:19-aplpine
WORKDIR /app
COPY package*.json .
RUN npm i --silent
COPY . .
RUN echo "VITE_API_BASE_URL=https://spring-boot-fullstack-v1-production.up.railway.app" > .env
EXPOSE 5173
CMD ["npm","run","dev"]