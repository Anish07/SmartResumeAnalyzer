# Smart Resume Analyzer

A full-stack application that leverages Google's Gemini AI to analyze resumes, extract key insights, and provide actionable feedback for job seekers.

## Features

-   **📄 Resume Parsing**: Automatically extracts text from PDF resumes using Apache Tika.
-   **🤖 AI Analysis**: Uses Google Gemini 2.5 Flash Lite to summarize resumes, identify top skills, and rate job fit.
-   **📊 Interactive Dashboard**: Visualizes analysis results with a clean, modern UI.
-   **🔐 Secure Authentication**: User registration and login with JWT-based security.
-   **📝 Cover Letter Generation**: (Coming Soon) Generate tailored cover letters based on your resume and a job description.

## Tech Stack

### Backend
-   **Framework**: Spring Boot 3 (Java 17+)
-   **Database**: H2 Database (In-memory)
-   **AI Integration**: Google Gemini API
-   **Security**: Spring Security + JWT
-   **Tools**: Apache Tika (PDF Parsing), Maven

### Frontend
-   **Framework**: Angular 19
-   **Styling**: SCSS, Modern CSS Variables
-   **HTTP Client**: Angular HttpClient

## Getting Started

### Prerequisites
-   **Java 17** or higher
-   **Node.js 18** or higher
-   **Google Gemini API Key**: Get one [here](https://aistudio.google.com/app/apikey)

### Backend Setup

1.  Navigate to the backend directory:
    ```bash
    cd backend
    ```
2.  Set your Gemini API key as an environment variable:
    ```bash
    export GEMINI_API_KEY="your_api_key_here"
    ```
3.  Run the application:
    ```bash
    ./mvnw spring-boot:run
    ```
    The backend will start on `http://localhost:8080`.

### Frontend Setup

1.  Navigate to the frontend directory:
    ```bash
    cd frontend
    ```
2.  Install dependencies:
    ```bash
    npm install
    ```
3.  Start the development server:
    ```bash
    ng serve
    ```
    The application will be available at `http://localhost:4200`.

## Usage

1.  Open your browser and go to `http://localhost:4200`.
2.  **Register** a new account or **Login** if you already have one.
3.  Navigate to the **Upload** page.
4.  Select a PDF resume and click "Analyze".
5.  View the AI-generated summary, skills, and rating on the **Dashboard**.

## License

This project is licensed under the MIT License. Built by Anish Ahuja.
