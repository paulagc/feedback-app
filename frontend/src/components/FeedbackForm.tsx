import React, {FC, useEffect, useState} from "react";
import "./Feedback.css";
import {submitFeedback} from "../api/feedbackAPI";

const enum serverStatus {
    idle,
    failure,
    success,
}

const FeedbackForm: FC<{ onSubmitted?: () => void }> = ({onSubmitted}) => {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [message, setMessage] = useState("");
    const [submitStatus, setSubmitStatus] = useState<serverStatus>(serverStatus.idle);

    const resetFields = () => {
        setName("");
        setEmail("");
        setMessage("");
    }

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            await submitFeedback({name, email, message});
            setSubmitStatus(serverStatus.success);
            onSubmitted && onSubmitted();
            resetFields();
        } catch {
            setSubmitStatus(serverStatus.failure);
        }
    }

    const closePopup = () => {
        setSubmitStatus(serverStatus.idle);
    }

    useEffect(() => {
        const timer = setTimeout(closePopup, 5000);
        return () => clearTimeout(timer);
    }, [submitStatus]);

    return (
        <div className="form-container">
            <h2>Feedback Form</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Name:
                    <input type="text" name="name" value={name} onChange={(e) => setName(e.target.value)} required/>
                </label>
                <label>
                    E-mail:
                    <input type="email" name="E-mail" value={email} onChange={(e) => setEmail(e.target.value)}
                           required/>
                </label>
                <label>
                    Feedback message:
                    <textarea name="message" value={message} onChange={(e) => setMessage(e.target.value)} required/>
                </label>
                <button>
                    Submit
                </button>
            </form>
            {
                submitStatus !== serverStatus.idle && (
                    <div className="popup">
                        {submitStatus === serverStatus.success ? "Feedback saved successfully" : "Feedback submit failed, please try again"}
                    </div>
                )
            }
        </div>
    );
};

export default FeedbackForm;