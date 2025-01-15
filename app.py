from flask import Flask, request, jsonify
import dill
import pandas as pd
from sklearn.feature_extraction.text import CountVectorizer  # <-- Import CountVectorizer here
from sklearn.metrics.pairwise import cosine_similarity

app = Flask(__name__)

# Load the model and data
with open("Includify.dill", "rb") as f:
    recommend_jobs, jobs_df = dill.load(f)

@app.route('/recommend', methods=['POST'])
def recommend():
    data = request.json
    user_skills = data["skills"]
    user_location = data["location"]
    user_experience = data["experience"]
    
    # Get job recommendations using the model
    recommendations = recommend_jobs(user_skills, user_location, user_experience)
    
    return jsonify(recommendations)

if __name__ == "__main__":
    app.run(debug=True)