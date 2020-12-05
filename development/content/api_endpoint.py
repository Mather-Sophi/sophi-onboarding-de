from dataprocessor import filereader
from flask import Flask
app = Flask(__name__)


filegw = filereader()

@app.route('/')
def runningapi():
    output = filegw.readingcontent()
    return output


if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')