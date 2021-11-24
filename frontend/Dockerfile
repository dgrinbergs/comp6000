#https://vuejs.org/v2/cookbook/dockerize-vuejs-app.html
FROM node:lts-alpine as build-stage
ENV VUE_APP_API_URL=$VUE_APP_API_URL
ENV VUE_APP_API_PORT=$VUE_APP_API_PORT
RUN npm install -g @vue/cli
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:stable-alpine as production-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]