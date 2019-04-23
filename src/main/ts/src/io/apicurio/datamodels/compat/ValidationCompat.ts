import { ValidationRuleMetaData } from '../core/validation/ValidationRuleMetaData';
import { ValidationRule } from '../core/validation/ValidationRule';

export class ValidationCompat {

    public static instantiate(ruleInfo: ValidationRuleMetaData): ValidationRule {
        return new ruleInfo.ruleClass(ruleInfo);
    }
}