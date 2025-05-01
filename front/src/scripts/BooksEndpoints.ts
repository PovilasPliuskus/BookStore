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
