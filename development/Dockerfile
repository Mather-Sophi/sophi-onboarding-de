FROM ubuntu:18.04

RUN apt-get update
CMD mkdir sophi
WORKDIR sophi/
COPY . /sophi/

RUN apt-get update && apt-get install -y software-properties-common
RUN add-apt-repository ppa:deadsnakes/ppa
RUN apt-get update && apt-get install -y python3.7 python3-pip

RUN pip3 install -r content/requirements.txt

CMD [ "python3","content/api_endpoint.py" ]