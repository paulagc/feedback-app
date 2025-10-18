const BASE_URL = "http://localhost:8080/api/feedback";

export interface Feedback {
    id?: number;
    name: string;
    email: string;
    message: string;
}

export const submitFeedback = async (feedback: Feedback) => {
    const response = await fetch(BASE_URL, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(feedback),
    });
    if (!response.ok) {
        throw new Error(`Failed to submit feedback: ${response.status}`);
    }
    return response.json();
}

export async function getAllFeedback(): Promise<Feedback[]> {
    const response = await fetch(BASE_URL);
    if (!response.ok) {
        throw new Error(`Failed to retrieve feedback: ${response.status}`);
    }
    return response.json();
}