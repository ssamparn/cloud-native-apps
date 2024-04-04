FROM nginx
COPY /html/index.html /usr/share/nginx/html/index.html
COPY /conf/nginx.conf /etc/nginx/nginx.conf
EXPOSE 9090
CMD ["nginx", "-g", "daemon off;"]