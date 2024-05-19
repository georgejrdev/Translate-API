from src import *
from flask import Flask
from flask_cors import CORS, cross_origin

app = Flask(__name__)
CORS(app)


@cross_origin
@app.route('/getTranslation/<string:text>|<dest>', methods=['GET'])
def get_translation(text,dest):
    return translate_text(text,dest)