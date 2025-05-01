import React, { useEffect, useState } from "react";
import {
  fetchAllBooks,
  fetchBook,
  UpdateBook,
  AddBook,
} from "../scripts/BooksEndpoints.ts";
import { CreateBookRequest, UpdateBookRequest } from "../interfaces.ts";

type Book = {
  id: number;
  title: string;
  pageCount: number;
};

function Books() {
  const [books, setBooks] = useState<Book[]>([]);
  const [newBook, setNewBook] = useState<CreateBookRequest>({
    title: "",
    pageCount: 0,
  });
  const [showModal, setShowModal] = useState(false);
  const [editBookId, setEditBookId] = useState<number | null>(null);
  const [editBookData, setEditBookData] = useState<UpdateBookRequest>({
    id: "",
    title: "",
    pageCount: 0,
    version: 0,
  });

  const loadBooks = async () => {
    try {
      const data = await fetchAllBooks();
      setBooks(data);
    } catch (error) {
      console.error("Error loading books:", error);
    }
  };

  useEffect(() => {
    loadBooks();
  }, []);

  const handleInputChange = (
    e: React.ChangeEvent<HTMLInputElement>,
    isEdit = false
  ) => {
    const { name, value } = e.target;
    if (isEdit) {
      setEditBookData((prev) => ({
        ...prev,
        [name]: name === "pageCount" ? Number(value) : value,
      }));
    } else {
      setNewBook((prev) => ({
        ...prev,
        [name]: name === "pageCount" ? Number(value) : value,
      }));
    }
  };

  const handleAddBook = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await AddBook(newBook);
      setShowModal(false);
      setNewBook({ title: "", pageCount: 0 });
      loadBooks();
    } catch (error) {
      console.error("Error adding book:", error);
    }
  };

  const handleEdit = async (bookId: number) => {
    try {
      const book = await fetchBook(bookId.toString());
      setEditBookData(book);
      setEditBookId(bookId);
    } catch (error) {
      console.error("Error fetching book:", error);
    }
  };

  const handleUpdateBook = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const response = await UpdateBook(editBookData);

      if (response.ok) {
        setEditBookId(null);
        loadBooks();
      } else if (response.status === 409) {
        const userConfirmed = window.confirm(
          "This book was modified by someone else. Do you want to override their changes?"
        );

        if (userConfirmed) {
          // Retry with force update (add flag if supported by backend)
          const overrideData = { ...editBookData, forceUpdate: true };
          const overrideResponse = await fetch(
            "http://localhost:9999/BookStore/api/book",
            {
              method: "PUT",
              headers: {
                "Content-Type": "application/json",
              },
              body: JSON.stringify(overrideData),
            }
          );

          if (overrideResponse.ok) {
            alert("Changes overridden successfully.");
            setEditBookId(null);
            loadBooks();
          } else {
            alert("Override failed. Please try again.");
          }
        } else {
          alert("Update cancelled.");
        }
      } else {
        const text = await response.text();
        alert(`Update failed: ${text}`);
      }
    } catch (error) {
      console.error("Error updating book:", error);
      alert("An error occurred while updating the book.");
    }
  };

  return (
    <div className="container mt-4">
      <h1 className="mb-4">Book List</h1>

      {books.length === 0 ? (
        <p>No books available.</p>
      ) : (
        <table className="table table-bordered table-striped">
          <thead className="table-dark">
            <tr>
              <th>ID</th>
              <th>Title</th>
              <th>Page Count</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {books.map((book) =>
              editBookId === book.id ? (
                <tr key={book.id}>
                  <td>{book.id}</td>
                  <td>
                    <input
                      type="text"
                      name="title"
                      value={editBookData.title}
                      onChange={(e) => handleInputChange(e, true)}
                      className="form-control"
                    />
                  </td>
                  <td>
                    <input
                      type="number"
                      name="pageCount"
                      value={editBookData.pageCount}
                      onChange={(e) => handleInputChange(e, true)}
                      className="form-control"
                    />
                  </td>
                  <td>
                    <button
                      className="btn btn-success btn-sm"
                      onClick={handleUpdateBook}
                    >
                      Save
                    </button>{" "}
                    <button
                      className="btn btn-secondary btn-sm"
                      onClick={() => setEditBookId(null)}
                    >
                      Cancel
                    </button>
                  </td>
                </tr>
              ) : (
                <tr key={book.id}>
                  <td>{book.id}</td>
                  <td>{book.title}</td>
                  <td>{book.pageCount}</td>
                  <td>
                    <button
                      className="btn btn-primary btn-sm"
                      onClick={() => handleEdit(book.id)}
                    >
                      Edit
                    </button>
                  </td>
                </tr>
              )
            )}
          </tbody>
        </table>
      )}

      <button
        className="btn btn-primary mt-4"
        onClick={() => setShowModal(true)}
      >
        Add Book
      </button>

      {showModal && (
        <>
          <div
            className="modal fade show"
            style={{ display: "block" }}
            tabIndex={-1}
          >
            <div className="modal-dialog">
              <div className="modal-content">
                <div className="modal-header">
                  <h5 className="modal-title">Add New Book</h5>
                  <button
                    type="button"
                    className="btn-close"
                    onClick={() => setShowModal(false)}
                  />
                </div>
                <div className="modal-body">
                  <form onSubmit={handleAddBook}>
                    <div className="mb-3">
                      <label htmlFor="title" className="form-label">
                        Title
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        id="title"
                        name="title"
                        value={newBook.title}
                        onChange={(e) => handleInputChange(e)}
                        required
                      />
                    </div>
                    <div className="mb-3">
                      <label htmlFor="pageCount" className="form-label">
                        Page Count
                      </label>
                      <input
                        type="number"
                        className="form-control"
                        id="pageCount"
                        name="pageCount"
                        value={newBook.pageCount}
                        onChange={(e) => handleInputChange(e)}
                        required
                      />
                    </div>
                    <button type="submit" className="btn btn-primary">
                      Add Book
                    </button>
                  </form>
                </div>
              </div>
            </div>
          </div>
          <div
            className="modal-backdrop fade show"
            onClick={() => setShowModal(false)}
          />
        </>
      )}
    </div>
  );
}

export default Books;
