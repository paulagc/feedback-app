import React, {useEffect, useState} from 'react';
import FeedbackForm from "./components/FeedbackForm";
import FeedbackList from "./components/FeedbackList";
import {Feedback, getAllFeedback} from "./api/feedbackAPI";

function App() {
    const [feedback, setFeedback] = useState<Feedback[]>([]);
    const loadFeedback = async () => {
        const data = await getAllFeedback();
        setFeedback(data);
    };

    useEffect(() => {
        loadFeedback();
    }, []);

    return (
        <div className="page-container">
            <FeedbackForm onSubmitted={loadFeedback}/>
            <FeedbackList feedback={feedback}/>
        </div>
    );
}

export default App;