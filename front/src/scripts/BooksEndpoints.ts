import { CreateBookRequest, UpdateBookRequest } from "../interfaces";

export const fetchAllBooks = async (): Promise<any> => {
  try {
    const response = await fetch("http://localhost:9999/BookStore/api/book", {
      method: "GET",
    });

    if (!response.ok) {
      throw new Error(`Error fetching data: ${response.statusText}`);
    }

    console.log("Retrieved response calling fetchAllBooks: ", response);

    return await response.json();
  } catch (error) {
    console.error("Error in fetchAllBooks: ", error);
    throw error;
  }
};

export const fetchBook = async (bookId: string): Promise<any> => {
  try {
    const response = await fetch(
      `http://localhost:9999/BookStore/api/book/${bookId}`,
      {
        method: "GET",
      }
    );

    if (!response.ok) {
      throw new Error(`Error fetching data: ${response.statusText}`);
    }

    console.log("Retrieved response calling fetchBook: ", response);

    return await response.json();
  } catch (error) {
    console.error("Error in fetchBook: ", error);
    throw error;
  }
};

export const UpdateBook = async (
  updateBookRequest: UpdateBookRequest
): Promise<Response> => {
  return fetch("http://localhost:9999/BookStore/api/book", {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(updateBookRequest),
  });
};

export const AddBook = async (newBook: CreateBookRequest): Promise<any> => {
  try {
    const response = await fetch("http://localhost:9999/BookStore/api/book", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newBook),
    });

    if (!response.ok) {
      console.error(`Error adding book: ${response.statusText}`);
      return { success: false };
    }

    const responseData = await response.json();
    console.log("Book added successfully: ", responseData);
  } catch (error) {
    console.error("Error adding Book", error);
    return { success: false };
  }
};
