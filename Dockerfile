FROM nginx

COPY ./back /usr/share/todo/back
COPY ./front /usr/share/todo/front
