import React, { useState, memo } from "react";
import "./List.css";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import PropTypes from "prop-types";


const SingleListItem = memo(({ index, text, isSelected, onClick, onDelete }) => {
  const handleDelete = () => {
    onDelete(index);
    toast.error(`Deleted "${text}"`);
  };

  return (
    <li
      className="list-item"
      style={{ backgroundColor: isSelected ? "green" : "red" }}
      onClick={() => onClick(index)}
    >
      {text}
      <div className="item-buttons">
        <button className="delete-button" onClick={handleDelete}>
          Delete
        </button>
      </div>
    </li>
  );
});

SingleListItem.propTypes = {
  index: PropTypes.number.isRequired,
  text: PropTypes.string.isRequired,
  isSelected: PropTypes.bool.isRequired,
  onClick: PropTypes.func.isRequired,
  onDelete: PropTypes.func.isRequired,
};

const List = memo(({ items, updateItems }) => {
  const [selectedIndexes, setSelectedIndexes] = useState([]);
  const [isMultiSelectMode, setIsMultiSelectMode] = useState(false);

  const handleClick = (index) => {
    const newIndexes = isMultiSelectMode ? [...selectedIndexes] : [];
    if (!newIndexes.includes(index)) {
      newIndexes.push(index);
      toast.success(`Selected "${items[index].text}"`);
    } else {
      newIndexes.splice(newIndexes.indexOf(index), 1);
      toast.warning(`Deselected "${items[index].text}"`);
    }
    setSelectedIndexes(newIndexes);
  };

  const handleDelete = (index) => {
    const itemToDelete = items[index].text;
    const newItems = items.filter((item, i) => i !== index);
    setSelectedIndexes(selectedIndexes.filter((i) => i !== index));
    updateItems(newItems);
    toast.error(`Deleted "${itemToDelete}"`);
  };

  const handleClear = () => setSelectedIndexes([]);
  const handleSelectAll = () => setSelectedIndexes(items.map((_, i) => i));
  const handleModeChange = () => setIsMultiSelectMode((prev) => !prev);

  return (
    <div className="list-container">
      <div className="selection-buttons mode-btn">
        {isMultiSelectMode && (
          <>
            <button className="list-control-button" onClick={handleClear}>
              Clear
            </button>
            <button className="list-control-button" onClick={handleSelectAll}>
              Select All
            </button>
          </>
        )}
        <button className="list-control-button " onClick={handleModeChange}>
          {isMultiSelectMode ? "Single Select" : "Multi-Select"}
        </button>
      </div>
      <ul className="list">
        {items.map((item, index) => (
          <SingleListItem
            key={index}
            index={index}
            text={item.text}
            isSelected={selectedIndexes.includes(index)}
            onClick={handleClick}
            onDelete={handleDelete}
          />
        ))}
      </ul>
      <ToastContainer position="top-right" />
    </div>
  );
});

List.propTypes = {
  items: PropTypes.arrayOf(
    PropTypes.shape({
      text: PropTypes.string.isRequired,
    })
  ).isRequired,
  updateItems: PropTypes.func.isRequired,
};

export default List;