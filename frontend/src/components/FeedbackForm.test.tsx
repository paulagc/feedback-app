import {render, screen, fireEvent} from "@testing-library/react";
import FeedbackForm from "./FeedbackForm";
import * as api from "../api/feedbackAPI";

jest.mock("../api/feedbackAPI");

test("submits feedback successfully", async () => {
    (api.submitFeedback as jest.Mock).mockResolvedValue({
        id: 1,
        name: "John Doe",
        email: "john@example.com",
        message: "Your platform looks great!"
    });

    render(<FeedbackForm/>);
    fireEvent.change(screen.getByLabelText(/Name:/i), {target: {value: "John Doe"}});
    fireEvent.change(screen.getByLabelText(/E-mail:/i), {target: {value: "john@example.com"}});
    fireEvent.change(screen.getByLabelText(/Feedback message:/i), {target: {value: "Your platform looks great!"}});
    fireEvent.click(screen.getByText(/Submit/i));

    expect(await screen.findByText(/Feedback saved successfully/i)).toBeInTheDocument();
});

test("shows error if API fails", async () => {
    (api.submitFeedback as jest.Mock).mockRejectedValue(new Error("Server error"));

    render(<FeedbackForm/>);
    fireEvent.change(screen.getByLabelText(/Name:/i), {target: {value: "John Doe"}});
    fireEvent.change(screen.getByLabelText(/E-mail:/i), {target: {value: "john@example.com"}});
    fireEvent.change(screen.getByLabelText(/Feedback message:/i), {target: {value: "Your platform looks great!"}});
    fireEvent.click(screen.getByText(/Submit/i));

    expect(await screen.findByText(/Feedback submit failed, please try again/i)).toBeInTheDocument();
});
