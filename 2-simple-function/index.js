const nums = [15, 11, 2, 7]
const target = 9

function findIndexesToSumForGetTarget(arr, target) {
  let result = [];
  for (let i = 0; i < arr.length; i++) {
    for (let j = i + 1; j < arr.length; j++) {
      if (arr[i] + arr[j] === target) {
        result.push(i, j);
      }
    }
  }

  if(result.length === 0) console.log("No hay ninguna combinación válida.")

  return result;
}

const result = findIndexesToSumForGetTarget(nums, target);
console.log(result);