# Steeleye Assignment Front End :- 

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------

**Quetion 1 :- Explain what the simple List component does.**

**Answer :-** 

The simple List component is a fundamental UI (user interface) component that allows you to display a list of data items in a structured format. It provides a straightforward and intuitive way to present a collection of items in a vertical or horizontal orientation, depending on the design.

Typically, each item in the list is represented by a simple layout, which consists of one or more text labels, images, icons, or other UI elements. The list can be scrollable to accommodate a large number of items, and each item can be selected or clicked to trigger a specific action or display more detailed information.

The simple List component is widely used in many types of applications, such as e-commerce sites, social media platforms, news apps, and many others. It provides a flexible and versatile way to display data in a clear and organized way, making it easier for users to navigate and interact with the content.

*Let us understand it with a simple example :-*

Imagine you have a shopping list on a piece of paper. Each item on the list is written in a separate line, and you can easily see what you need to buy. This list is like a simple List component in a shopping app or website, where each item (e.g., milk, bread, eggs, etc.) is displayed in a structured format, making it easy for you to read and interact with the content.

In the shopping app, you can scroll through the list if you have many items to buy, and you can click on each item to see more information, such as the price or quantity. You can also select each item as you add it to your cart or remove it from the list if you change your mind.

So, the simple List component is like a digital version of your shopping list that helps you organize and manage your tasks more efficiently. It's a basic building block for many types of apps and websites, providing a simple and intuitive way to display data in a list format.

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------

**Question 2 :- What problems / warnings are there with code?**

**Answer :-** 

The given code is a React component that renders a list of items and allows the user to select a single item from the list. There are several potential issues and warnings that can be addressed in this code:

1. **PropTypes warning:** The PropTypes library is used to validate the props passed to the components. The PropTypes for items in the WrappedListComponent component      is not defined correctly. Instead of PropTypes.array(PropTypes.shapeOf({text: PropTypes.string.isRequired})), it should be PropTypes.arrayOf(PropTypes.shape({text:
   PropTypes.string.isRequired})). This defines the items prop as an array of objects with a required text property.

2. **State update warning:** The setSelectedIndex function in the WrappedListComponent component is used to update the state of the component. However, the code calls
   setSelectedIndex with a null value in the useEffect hook, which causes an infinite loop of state updates. Instead, setSelectedIndex should be called with an initial
   value of -1 or null outside the useEffect hook.

3. **isSelected prop warning:** The isSelected prop in the WrappedSingleListItem component is used to determine whether or not the current item is selected. However,      the isSelected prop is passed to the component as isSelected={selectedIndex}, which passes the entire selectedIndex state object instead of the expected boolean        value. Instead, isSelected={selectedIndex === index} should be used to check if the current item's index matches the selectedIndex.

4. **onClickHandler warning:** The onClickHandler prop in the WrappedSingleListItem component is defined as a required function prop. However, it is called with a        value (onClickHandler(index)) in the component's render method, which can cause performance issues. To fix this issue, it can be defined as an optional function        prop with a default value of an empty function like onClickHandler: PropTypes.func.

5. **Missing key prop warning:** When rendering a list in React, each item should have a unique key prop. In the WrappedListComponent component, the index prop is        passed to the SingleListItem component, but it is not used as a key prop. To fix this issue, key={index} should be added to the SingleListItem component.

6. **Unused code warning:** The memo function is used to memoize the components, which can improve performance by avoiding unnecessary re-renders. However, the memo
   function is not needed for the WrappedSingleListItem component, as it only receives props and does not have any internal state or lifecycle methods. Similarly, memo
   is not necessary for the WrappedListComponent component, as it is already memoized by being wrapped in memo(ListedComponent) at the end of the code. Therefore, the
   memo function can be removed from both components.

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------

**Question 3 :- Please fix, optimize, and/or modify the component as much as you think is necessary.**

**Answer :-**

**Here is the optimised code :-**

```
import React, { useState, useEffect, useCallback, memo } from 'react';
import PropTypes from 'prop-types';

// Single List Item
const SingleListItem = memo(({ index, isSelected, onClickHandler, text }) => {
  return (
    <li
      style={{ backgroundColor: isSelected ? 'green' : 'red' }}
      onClick={() => onClickHandler(index)}
    >
      {text}
    </li>
  );
});

SingleListItem.propTypes = {
  index: PropTypes.number,
  isSelected: PropTypes.bool,
  onClickHandler: PropTypes.func.isRequired,
  text: PropTypes.string.isRequired,
};

// List Component
const List = memo(({ items }) => {
  const [selectedIndex, setSelectedIndex] = useState(null);

  useEffect(() => {
    setSelectedIndex(null);
  }, [items]);

  const handleClick = useCallback(index => {
    setSelectedIndex(index);
  }, []);

  return (
    <ul style={{ textAlign: 'left' }}>
      {items &&
        items.map((item, index) => (
          <SingleListItem
            onClickHandler={handleClick}
            text={item.text}
            index={index}
            isSelected={selectedIndex === index}
            key={index}
          />
        ))}
    </ul>
  );
});

List.propTypes = {
  items: PropTypes.arrayOf(
    PropTypes.shape({
      text: PropTypes.string.isRequired,
    })
  ),
};

List.defaultProps = {
  items: [],
};

export default List;
```
