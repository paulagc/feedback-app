import React, {FC} from "react";
import {Feedback} from "../api/feedbackAPI";
import "./Feedback.css";

const FeedbackList: FC<{ feedback: Feedback[] }> = ({feedback}) => {
    return (
        <div className="form-container">
            <h2>Feedback list</h2>
            <ul>
                {feedback.map(f => (
                    <li key={f.id} className="feedback-item">
                        Name: {f.name} <br/>
                        Email: {f.email}<br/>
                        Message: <br/>
                        {f.message}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default FeedbackList;
