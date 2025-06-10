class Solution {
    public void sortColors(int[] nums) {
        boolean swapped;
        int n=nums.length;
        for(int i=0;i<n;i++){
            swapped=false;
            for(int j=0;j<n-i-1;j++){
                int temp;
                if(nums[j]>nums[j+1]){
                    temp=nums[j];
                    nums[j]=nums[j+1];
                    nums[j+1]=temp;
                    swapped=true;
                }
            }
            if(!swapped)break;
           }
        }
}
