import React, { useEffect } from "react";
import { fetchAllBooks } from "../scripts/BooksEndpoints.ts";

function Books() {
  const loadBooks = async () => {
    try {
      const books = await fetchAllBooks();
      console.log(books);
    } catch (error) {
      console.error("Error loading all books: ", error);
    }
  };

  useEffect(() => {
    loadBooks();
  }, []);

  return (
    <>
      <h1>Hello World from Books</h1>
    </>
  );
}

export default Books;
