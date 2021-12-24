import json

from app import app  # app: Ứng dụng flask

from flask import render_template, flash, request, redirect, url_for, jsonify


# set ENV_NAME=app.py: set up biến môi trường
# pip3 install python-dotenv + file .flaskenv lưu ENV_NAME=app.py để tự động tham chiếu tới Flask
@app.route('/')
def hello_world():
    return 'Web App with Python Flask!'


@app.route('/request_bike_id', methods=['POST'])
def convert_bike_id():
    if request.method == 'POST':
        barcode = request.args['code']
        inverse_barcode = ''
        for c in barcode:
            inverse_barcode += '0' if c == '1' else '1'
        bikeId = int(inverse_barcode, 2)
        return jsonify({'bikeId': bikeId})
