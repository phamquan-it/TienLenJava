package controls;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.javagame.SpringContext;
import com.javagame.entities.Card;

/**
 * Bộ não AI cho Tiến Lên.
 *
 * Chiến lược đơn giản nhưng đúng luật:
 *  - Đi đầu ván: đánh lá nhỏ nhất (không xé bộ khi có thể).
 *  - Theo lượt: tìm bộ hợp lệ nhỏ nhất có thể đè lên bài trên bàn.
 *    Nếu không đè được (hoặc lá phải dùng là 2) thì bỏ lượt.
 *
 * Chỉ dùng các hàm luật đã có sẵn (CardsValid / CardsCompare) nên
 * không viết lại luật lần thứ hai.
 */
@Component
public class BotPlayer implements SpringContext {

	/**
	 * @param hand      bài trên tay của bot
	 * @param onTable   bài đang nằm trên bàn (rỗng = bot được đi đầu)
	 * @return danh sách lá bot muốn đánh; rỗng = bot bỏ lượt
	 */
	public List<Card> chooseCards(List<Card> hand, List<Card> onTable) {
		if (hand == null || hand.isEmpty())
			return new ArrayList<>();
		List<Card> sorted = new ArrayList<>(hand);
		Collections.sort(sorted);
		if (onTable == null || onTable.isEmpty())
			return Collections.singletonList(sorted.getFirst());
		return findSmallestBeating(sorted, onTable);
	}

	/** Duyệt từ bộ nhỏ nhất, trả về bộ đầu tiên đè được bài trên bàn. */
	private List<Card> findSmallestBeating(List<Card> sorted, List<Card> onTable) {
		// Lá đơn: chỉ cần tìm 1 lá lớn hơn lá trên bàn
		if (cardsValid.isSingleCard(onTable)) {
			Card target = cardDataSupport.getMaxCard(onTable);
			for (Card card : sorted) {
				if (cardsCompare.singleCardCompare(List.of(card), List.of(target)))
					return List.of(card);
			}
			return new ArrayList<>();
		}
		// Các loại bộ nhiều lá: chỉ xét bộ cùng số lá với bài trên bàn
		int n = onTable.size();
		if (n > sorted.size())
			return new ArrayList<>();
		for (int width = 1; width <= 3; width++) {
			if (n % width != 0)
				continue;
			int group = n / width;
			for (int start = 0; start + n <= sorted.size(); start++) {
				List<Card> candidate = new ArrayList<>(sorted.subList(start, start + n));
				if (!groupsTogether(candidate, group))
					continue;
				if (cardsCompare.getResultCompare(candidate, onTable)
						|| cardsCompare.isMinimumPower(candidate, onTable)
						|| cardsCompare.isMediumPower(candidate, onTable)
						|| cardsCompare.isUntilmatePower(candidate, onTable))
					return candidate;
			}
		}
		return new ArrayList<>();
	}

	/**
	 * Kiểm tra bộ ứng viên có phải dạng "group lá giống nhau liên tiếp" không.
	 * Cắt bộ con từ list đã sort như trên luôn đúng dạng này, nhưng kiểm tra
	 * vẫn cần để tránh lấy nhầm bộ 3 lá lẻ.
	 */
	private boolean groupsTogether(List<Card> cards, int groupSize) {
		for (int i = 0; i < cards.size(); i += groupSize) {
			for (int j = i + 1; j < i + groupSize && j < cards.size(); j++) {
				if (cards.get(i).getId() != cards.get(j).getId())
					return false;
			}
			if (i > 0 && cards.get(i - 1).getId() >= cards.get(i).getId())
				return false;
		}
		return true;
	}
}
