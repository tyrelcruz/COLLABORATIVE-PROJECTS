import dayjs from 'https://unpkg.com/dayjs@1.11.10/esm/index.js';
export function formatTime(post){
  let postTime = dayjs(post.time);
    let now = dayjs();

    let displayedTime;

    let secondsAgo = now.diff(postTime, 'second');
    let minutesAgo = now.diff(postTime, 'minute');
    let hoursAgo = now.diff(postTime, 'hour');
    let daysAgo = now.diff(postTime, 'day');

    if (secondsAgo < 60) {
      displayedTime = secondsAgo > 1 ? `${secondsAgo} seconds ago` : `${secondsAgo} second ago`;
    } else if (minutesAgo < 60) {
      displayedTime = minutesAgo > 1 ? `${minutesAgo} minutes ago` : `${minutesAgo} minute ago`;
    } else if (hoursAgo < 24) {
      displayedTime = hoursAgo > 1 ? `${hoursAgo} hours ago` : `${hoursAgo} hour ago`;
    } else {
      displayedTime = daysAgo > 1 ? `${daysAgo} days ago` : `${daysAgo} day ago`;
    }
    return displayedTime;
}