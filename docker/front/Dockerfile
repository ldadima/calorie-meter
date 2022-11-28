FROM node:14.4.0

EXPOSE 8080
EXPOSE 3000

WORKDIR /usr/web

COPY ./src/main/webapp ./
RUN npm install

CMD ["npm", "start"]
